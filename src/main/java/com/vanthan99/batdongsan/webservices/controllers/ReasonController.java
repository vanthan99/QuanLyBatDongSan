package com.vanthan99.batdongsan.webservices.controllers;

import com.vanthan99.batdongsan.entities.Reason;
import com.vanthan99.batdongsan.services.ReasonService;
import com.vanthan99.batdongsan.services.SupportService;
import com.vanthan99.batdongsan.webservices.payload.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/v1/reasons")
public class ReasonController {
    private ReasonService reasonService;

    @Autowired
    public void setReasonService(ReasonService reasonService) {
        this.reasonService = reasonService;
    }

    private SupportService supportService;

    @Autowired
    public void setSupportService(SupportService supportService) {
        this.supportService = supportService;
    }


    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> findAllReasons() {
        return new ResponseEntity<>(reasonService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> findReasonById(@PathVariable Long id) {
        Reason reason = reasonService.findById(id);
        if (reason == null) {
            return new ResponseEntity<>(new ApiResponse(false, "Can not find this reason!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reason, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createReason(@RequestBody Reason reason) {
        reasonService.save(reason);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reason.getId())
                .toUri();
        return ResponseEntity.created(location).body(reason);
    }

    @PatchMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateReason(@PathVariable Long id, @RequestBody Reason reason) {
        reason.setId(id);
        if (reasonService.findById(id) == null) {
            return new ResponseEntity<>(new ApiResponse(false, "Can not find this reason!"), HttpStatus.NOT_FOUND);
        }
        reasonService.save(reason);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reason.getId())
                .toUri();
        return ResponseEntity.created(location).body(reason);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteReason(@PathVariable Long id) {
        reasonService.delete(id);
        return new ResponseEntity<>(new ApiResponse(true, "Delete reason successfully!"), HttpStatus.OK);
    }
}
