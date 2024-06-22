package com.jsp.wms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Storage;
import com.jsp.wms.entity.StorageType;
import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.exception.StorageNotFoundByIdException;
import com.jsp.wms.exception.StorageTypeNotExistException;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.mapper.StorageMapper;
import com.jsp.wms.repository.StorageRepository;
import com.jsp.wms.repository.StorageTypeRepository;
import com.jsp.wms.repository.WareHouseRepository;
import com.jsp.wms.requestdto.StorageRequest;
import com.jsp.wms.responsedto.StorageResponse;
import com.jsp.wms.service.StorageService;
import com.jsp.wms.util.ResponseStructure;
import com.jsp.wms.util.SimpleStructure;
@Service
public class StorageServiceImpl implements StorageService{
	@Autowired
	StorageRepository storageRepository;
	@Autowired
	WareHouseRepository wareHouseRepository;
	@Autowired
	StorageMapper storageMapper;
	@Autowired
	StorageTypeRepository  storageTypeRepository;
	
	
    @Override
    public ResponseEntity<ResponseStructure<String>> createStorage(
            StorageRequest storageRequest,
            Integer wareHouseId,
            Integer storageTypeId,
            Integer noOfStorageUnits) {

        WareHouse wareHouse = wareHouseRepository.findById(wareHouseId).orElseThrow(() ->
                new WarehouseNotFoundByIdException(" wareHouseIdId : " +"wareHouseId"  + ", is not exist"));

         
		StorageType storageType = storageTypeRepository.findById(storageTypeId).orElseThrow(()->
                new StorageTypeNotExistException("StorageTypeId : "+storageTypeId+", StorageType is not exist"));

        storageType.setUnitsAvailable(storageType.getUnitsAvailable()+noOfStorageUnits);
        storageType = storageTypeRepository.save(storageType);


        double totalCapacity = storageType.getCapacityWeightInKg() * noOfStorageUnits + wareHouse.getTotalCapacityInKg();
        wareHouse.setTotalCapacityInKg(totalCapacity);
        wareHouseRepository.save(wareHouse);

        List<Storage> storages = new ArrayList<Storage>();
        while (noOfStorageUnits > 0) {
            Storage storage = storageMapper.mapToStorage(storageRequest, new Storage());
            storage.setWareHouse(wareHouse);
            storage.setStorageType(storageType);
            storage.setMaxAdditionalWeight(totalCapacity);
            storage.setAvailabelArea(storageType.getHeightInMeters() * storageType.getLengthInMeters() * storageType.getBreadthInMeters());

            storages.add(storage);
            noOfStorageUnits--;
        }

        storages = storageRepository.saveAll(storages);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<String>()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Storage Created")
                .setData(storages.size() + " storages are created"));
    }
    //--------------------------------------------------------------------------------------------------------------------
	@Override
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(StorageRequest storageRequest,
			Integer storageId) {
		
		return storageRepository.findById(storageId).map(exstorage->{ 

//			WareHouse wareHouse=exstorage.getWareHouse();
//			int totalCapacity = (storageRequest.getCapacityInKg() - exstorage.getCapacityInWeight()) + wareHouse.getTotalCapcity();
			
			storageMapper.mapToStorage(storageRequest, exstorage); 
			
//		    wareHouse.setTotalCapcity(totalCapacity);
//		    wareHouseRepository.save(wareHouse);
			exstorage = storageRepository.save(exstorage);
		
			    return ResponseEntity.status(HttpStatus.OK)
			.body(new ResponseStructure<StorageResponse>()
					.setStatus(HttpStatus.OK.value())
					.setMessage("updated storage sucessfully")
					.setData(storageMapper.mapToStorageResponse(exstorage)));
		}).orElseThrow(()->new StorageNotFoundByIdException("invalid storage id"));
		
		
		}
	public ResponseEntity<ResponseStructure<StorageResponse>> findStorage(Integer storageId){
	return	storageRepository.findById(storageId).map(storages->{
		return	ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<StorageResponse>()
					                                     .setStatus(HttpStatus.FOUND.value())
					                                     .setData(storageMapper.mapToStorageResponse(storages)));
		}).orElseThrow(()-> new StorageNotFoundByIdException("INVALID STORAGE ID "));
	}
	
	public ResponseEntity<ResponseStructure<List<StorageResponse>>> findAllStorages(){
		 List<StorageResponse> storages=storageRepository.findAll()
				.stream().map(storageMapper::mapToStorageResponse)
				.toList();

		return	ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<StorageResponse>>()
					.setMessage("storages found")
					.setStatus(HttpStatus.FOUND.value())
					.setData(storages));
	
	}
	

}
