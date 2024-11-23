package com.example.security.serviceImpl;

import com.example.security.dto.SolicitudDto;
import com.example.security.entity.*;
import com.example.security.repository.*;
import com.example.security.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitudServiceImpl implements SolicitudService {

	@Override
	public SolicitudDto getDatosIniciales() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveSolicitud(SolicitudDto solicitudDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SolicitudDto> listarSolicitudes() {
		// TODO Auto-generated method stub
		return null;
	}

 
    }

 