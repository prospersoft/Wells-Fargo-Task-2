package com.wellsfargo.counselor.controller;

import com.wellsfargo.counselor.entity.Security;
import com.wellsfargo.counselor.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/securities")
public class SecurityController {

    @Autowired
    private SecurityRepository securityRepository;

    @GetMapping
    public List<Security> getAllSecurities() {
        return securityRepository.findAll();
    }

    @GetMapping("/{id}")
    public Security getSecurityById(@PathVariable Long id) {
        return securityRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Security createSecurity(@RequestBody Security security) {
        return securityRepository.save(security);
    }

    @PutMapping("/{id}")
    public Security updateSecurity(@PathVariable Long id, @RequestBody Security securityDetails) {
        Security security = securityRepository.findById(id).orElse(null);
        if (security != null) {
            security.setName(securityDetails.getName());
            security.setCategory(securityDetails.getCategory());
            security.setPurchaseDate(securityDetails.getPurchaseDate());
            security.setPurchasePrice(securityDetails.getPurchasePrice());
            security.setQuantity(securityDetails.getQuantity());
            return securityRepository.save(security);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteSecurity(@PathVariable Long id) {
        securityRepository.deleteById(id);
    }
}
