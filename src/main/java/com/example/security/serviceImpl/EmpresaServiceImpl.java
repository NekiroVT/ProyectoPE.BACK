package com.example.security.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.entity.Empresa;
import com.example.security.repository.EmpresaRepository;
import com.example.security.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public Empresa create(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa update(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public void delete(Long id) {
        empresaRepository.deleteById(id);
    }

    @Override
    public Optional<Empresa> read(Long id) {
        return empresaRepository.findById(id);
    }

    @Override
    public List<Empresa> readAll() {
        return empresaRepository.findAll();
    }
}

