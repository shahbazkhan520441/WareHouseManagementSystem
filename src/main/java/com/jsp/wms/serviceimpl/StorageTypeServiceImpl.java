package com.jsp.wms.serviceimpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.StorageType;
import com.jsp.wms.exception.StorageTypeAlreadyExistException;
import com.jsp.wms.exception.StorageTypeNotExistException;
import com.jsp.wms.mapper.StorageTypeMapper;
import com.jsp.wms.repository.StorageRepository;
import com.jsp.wms.repository.StorageTypeRepository;
import com.jsp.wms.requestdto.StorageTypeRequest;
import com.jsp.wms.responsedto.StorageTypeResponse;
import com.jsp.wms.service.StorageTypeService;
import com.jsp.wms.util.ResponseStructure;

import java.util.List;

@Service
public class StorageTypeServiceImpl implements StorageTypeService {
    @Autowired
    private StorageTypeRepository storageTypeRepository;
   @Autowired
   private StorageTypeMapper storageTypeMapper;

    @Autowired
    private StorageRepository storageRepository;
    //--------------------------------------------------------------------------------------------------------------------

    @Override
    public ResponseEntity<ResponseStructure<StorageTypeResponse>> addStorageType(StorageTypeRequest storageTypeRequest) {
       boolean bl = storageTypeRepository.existsByLengthInMetersAndBreadthInMetersAndHeightInMetersAndCapacityWeightInKg(
                storageTypeRequest.getLengthInMeters(),
                storageTypeRequest.getBreadthInMeters(),
                storageTypeRequest.getHeightInMeters(),
                storageTypeRequest.getCapacityWeightInKg());

        if(!bl) {
            StorageType storageType = storageTypeMapper.mapStorageTypeRequestToStorageType(storageTypeRequest, new StorageType());
            storageType = storageTypeRepository.save(storageType);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<StorageTypeResponse>()
                    .setStatus(HttpStatus.CREATED.value())
                    .setMessage("StorageType Created")
                    .setData(storageTypeMapper.mapStorageTypeToStorageTypeResponse(storageType)));
        }else
            throw new StorageTypeAlreadyExistException("StorageType is already exist in database");
    }
    //--------------------------------------------------------------------------------------------------------------------
    @Override
    public ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(
            StorageTypeRequest storageTypeRequest, Integer storageTypeId) {
       return storageTypeRepository.findById(storageTypeId).map(storageType->{
        storageType = storageTypeMapper.mapStorageTypeRequestToStorageType(storageTypeRequest, storageType);

//        TODO update storeHouse is pending

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<StorageTypeResponse>()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("StoreType is updated")
                    .setData(storageTypeMapper.mapStorageTypeToStorageTypeResponse(storageType)));
        }).orElseThrow(()->new StorageTypeNotExistException("StorageTypeId : "+storageTypeId+", does not exist"));
    }
    //--------------------------------------------------------------------------------------------------------------------
    @Override
    public ResponseEntity<ResponseStructure<StorageTypeResponse>> findStorageType(Integer storageTypeId) {
        return storageTypeRepository.findById(storageTypeId).map(storageType->{
              return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<StorageTypeResponse>()
                      .setStatus(HttpStatus.FOUND.value())
                      .setMessage("StorageType Founded")
                      .setData(storageTypeMapper.mapStorageTypeToStorageTypeResponse(storageType)));
        }).orElseThrow(()->new StorageTypeNotExistException("StorageTypeId : "+storageTypeId+", storageType is not exist"));
    }
    //--------------------------------------------------------------------------------------------------------------------

    @Override
    public ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findStorageTypes() {
        List<StorageTypeResponse> listStorageTypeResponse = storageTypeRepository.findAll()
                .stream()
                .map(storageType -> storageTypeMapper.mapStorageTypeToStorageTypeResponse(storageType))
                .toList();
        return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<StorageTypeResponse>>()
                .setStatus(HttpStatus.FOUND.value())
                .setMessage("StorageType Founded")
                .setData(listStorageTypeResponse));
    }
	

    //--------------------------------------------------------------------------------------------------------------------


    //--------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------
//    /-----------------------------

}
