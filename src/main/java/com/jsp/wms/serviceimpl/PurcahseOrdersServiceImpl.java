package com.jsp.wms.serviceimpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Inventory;
import com.jsp.wms.entity.PurchaseOrders;
import com.jsp.wms.entity.Stock;
import com.jsp.wms.exception.InventoryNotExistException;
import com.jsp.wms.exception.PurchaseOrderNotCompletedException;
import com.jsp.wms.exception.PurchaseOrderNotExistException;
import com.jsp.wms.mapper.PurchaseOrdersMapper;
import com.jsp.wms.repository.InventoryRepository;
import com.jsp.wms.repository.PurchaseOrdersRepository;
import com.jsp.wms.requestdto.PurchaseOrdersRequest;
import com.jsp.wms.responsedto.PurchaseOrdersResponse;

import com.jsp.wms.service.PurcahseOrdersService;
import com.jsp.wms.util.ResponseStructure;
@Service
public class PurcahseOrdersServiceImpl implements PurcahseOrdersService {
	 @Autowired
	    private PurchaseOrdersRepository purchaseOrdersRepository;

	    @Autowired
	    private InventoryRepository inventoryRepository;

	    @Autowired
	    private PurchaseOrdersMapper purchaseOrdersMapper;
	    //--------------------------------------------------------------------------------------------------------------------

	    @Override
	    public ResponseEntity<ResponseStructure<PurchaseOrdersResponse>> addPurchaseOrder(
	            PurchaseOrdersRequest purchaseOrdersRequest, Integer inventoryId) {
	     return    inventoryRepository.findById(inventoryId).map(inventory -> {
	            PurchaseOrders purchaseOrder = purchaseOrdersMapper.mapToPurchaseOrders(purchaseOrdersRequest, new PurchaseOrders());
	            purchaseOrder.setInvoiceLink(UUID.randomUUID().toString().concat(".jpg"));

	            Integer availableQuantity = inventory.getStocks().getFirst().getStockQuantity();
	            if(availableQuantity>=purchaseOrdersRequest.getOrderQuantity()) {
	                Integer temp = availableQuantity - purchaseOrder.getOrderQuantity();
	                Stock stock = inventory.getStocks().getFirst();
	                stock.setStockQuantity(temp);
	                inventory.getStocks().addFirst(stock);

	                inventory = inventoryRepository.save(inventory);

	                purchaseOrder.setInventory(List.of(inventory));
	                purchaseOrder = purchaseOrdersRepository.save(purchaseOrder);
	            }else {
	                throw new PurchaseOrderNotCompletedException("Please reduce quantity");
	            }
	         return    ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<PurchaseOrdersResponse>()
	                    .setStatus(HttpStatus.CREATED.value())
	                    .setMessage("PurchaseOrder Created")
	                    .setData(purchaseOrdersMapper.mapToPurchaseOrdersResponse(purchaseOrder)));
	        }).orElseThrow(() -> new InventoryNotExistException("InventoryId : " + inventoryId + ", is not exist"));
	    }

	    //--------------------------------------------------------------------------------------------------------------------
//	    note : this method is only for demo purpose
	    @Override
	    public ResponseEntity<ResponseStructure<PurchaseOrdersResponse>> updatePurchaseOrder(
	            PurchaseOrdersRequest purchaseOrderRequest, Integer orderId) {
	    return     purchaseOrdersRepository.findById(orderId).map(purchaseOrder -> {
	            Integer oldOrderQnt = purchaseOrder.getOrderQuantity();
	            Integer newOrderQnt = purchaseOrderRequest.getOrderQuantity();

//	         TODO note :-> this method is working only if purchase order have only one inventory
	            List<Inventory> listInventories = purchaseOrder.getInventory();
	            if (newOrderQnt > oldOrderQnt) {
	                double updateOrderQnt = newOrderQnt - oldOrderQnt;
	                listInventories.forEach(inventory -> {
//	                    inventory.setQuantity(inventory.getQuantity()-updateOrderQnt);
	                    inventoryRepository.save(inventory);
	                });
	            } else {
	                Integer updateOrderQnt = oldOrderQnt - newOrderQnt;
	                listInventories.forEach(inventory -> {
//	                    inventory.setQuantity(inventory.getQuantity()+updateOrderQnt);
	                    inventoryRepository.save(inventory);
	                });
	            }
	            purchaseOrder = purchaseOrdersMapper.mapToPurchaseOrders(purchaseOrderRequest, purchaseOrder);
	         return    ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<PurchaseOrdersResponse>()
	                    .setStatus(HttpStatus.OK.value())
	                    .setMessage("PurchaseOrder Updated")
	                    .setData(purchaseOrdersMapper.mapToPurchaseOrdersResponse(purchaseOrder)));
	        }).orElseThrow(() -> new PurchaseOrderNotExistException("OrderId : " + orderId + ", is not exist"));
	    }
	    //--------------------------------------------------------------------------------------------------------------------

	    @Override
	    public ResponseEntity<ResponseStructure<PurchaseOrdersResponse>> findPurchaseOrder(Integer orderId) {
	     return    purchaseOrdersRepository.findById(orderId).map(purchaseOrder -> {
	    	 return  ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<PurchaseOrdersResponse>()
	                    .setStatus(HttpStatus.FOUND.value())
	                    .setMessage("PurchaseOrder Founded")
	                    .setData(purchaseOrdersMapper.mapToPurchaseOrdersResponse(purchaseOrder)));
	        }).orElseThrow(() -> new PurchaseOrderNotExistException("OrderId : " + orderId + ", is not exist"));
	    }
	    //--------------------------------------------------------------------------------------------------------------------
	    @Override
	    public ResponseEntity<ResponseStructure<List<PurchaseOrdersResponse>>> findPurchaseOrders() {
	        List<PurchaseOrdersResponse> listPurchaseOrders = purchaseOrdersRepository.findAll()
	                .stream()
	                .map(purchaseOrder ->
	                purchaseOrdersMapper.mapToPurchaseOrdersResponse(purchaseOrder)).toList();
	        return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<PurchaseOrdersResponse>>()
	                .setStatus(HttpStatus.FOUND.value())
	                .setMessage("PurchaseOrders are Founded")
	                .setData(listPurchaseOrders));
	    }

	    //-------------------------------------------------------------------



}
