		package com.jsp.wms.serviceimpl;
		
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.http.HttpStatus;
		import org.springframework.http.ResponseEntity;
		
		import com.jsp.wms.entity.Storage;
		import com.jsp.wms.exception.WarehouseNotFoundByIdException;
		import com.jsp.wms.mapper.StorageMapper;
		import com.jsp.wms.repository.StorageRepository;
		import com.jsp.wms.repository.WareHouseRepository;
		import com.jsp.wms.requestdto.StorageRequest;
		import com.jsp.wms.responsedto.StorageResponse;
		import com.jsp.wms.service.StorageService;
		import com.jsp.wms.util.ResponseStructure;
		
		public class StorageServiceImpl implements StorageService{
			@Autowired
		     StorageRepository storageRepository;
			@Autowired
			WareHouseRepository wareHouseRepository;
			@Autowired
			StorageMapper storageMapper;
			@Override
			public ResponseEntity<ResponseStructure<StorageResponse>> createStorage(StorageRequest storageRequest,Integer wareHouseId) {
				return wareHouseRepository.findById(wareHouseId).map(warehouse->{
					Storage storage=storageMapper.mapToStorage(storageRequest, new Storage());
					storage.setMaxAdditionalWeight(0);
					storage.setAvailabelArea(0);
					storageRepository.save(storage);
					 warehouse.setTotalCapcity(warehouse.getTotalCapcity()+storage.getAvailabelArea());
					  wareHouseRepository.save(warehouse);
					
					 
					  return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<StorageResponse>()
		                       .setStatus(HttpStatus.CREATED.value())
		                       .setMessage("storage created")
		                       .setData(storageMapper.mapToStorageResponse(storage)));
					   
				}).orElseThrow(()->new WarehouseNotFoundByIdException("inavlid warehouse id: "+wareHouseId+" not present"));
				
			}
		
		}
