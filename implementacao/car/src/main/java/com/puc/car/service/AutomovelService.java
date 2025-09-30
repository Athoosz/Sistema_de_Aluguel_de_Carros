package com.puc.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puc.car.dto.Automovel.AutomovelRegisterRequest;
import com.puc.car.exceptions.EntityNotFoundException;
import com.puc.car.models.Automovel;
import com.puc.car.models.Usuario;
import com.puc.car.repositories.AutomovelRepository;
import com.puc.car.repositories.UsuarioRepository;

import java.util.List;

@Service
public class AutomovelService {

    @Autowired
    private AutomovelRepository automovelRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Automovel criarAutomovel(AutomovelRegisterRequest request) {
        if (automovelRepository.existsByMatricula(request.matricula())) {
            throw new IllegalStateException("Já existe um automóvel com esta matrícula");
        }
        
        if (automovelRepository.existsByPlaca(request.placa())) {
            throw new IllegalStateException("Já existe um automóvel com esta placa");
        }

        Usuario proprietario = usuarioRepository.findById(request.proprietarioId())
                .orElseThrow(() -> new EntityNotFoundException("Proprietário não encontrado"));

        Automovel automovel = new Automovel();
        automovel.setMatricula(request.matricula());
        automovel.setPlaca(request.placa());
        automovel.setAno(request.ano());
        automovel.setMarca(request.marca());
        automovel.setModelo(request.modelo());
        automovel.setProprietario(proprietario);

        return automovelRepository.save(automovel);
    }

    public Automovel buscarPorId(Long id) {
        return automovelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Automóvel não encontrado"));
    }

    public List<Automovel> listarTodos() {
        return automovelRepository.findAll();
    }

    public List<Automovel> buscarPorProprietario(Long proprietarioId) {
        return automovelRepository.findByProprietarioId(proprietarioId);
    }

    public List<Automovel> buscarPorMarca(String marca) {
        return automovelRepository.findByMarcaContainingIgnoreCase(marca);
    }

    public Automovel atualizarAutomovel(Long id, AutomovelRegisterRequest request) {
        Automovel automovel = buscarPorId(id);

        // Verificar se matrícula já existe (exceto para o próprio automóvel)
        if (!automovel.getMatricula().equals(request.matricula()) && 
            automovelRepository.existsByMatricula(request.matricula())) {
            throw new IllegalStateException("Já existe um automóvel com esta matrícula");
        }
        
        // Verificar se placa já existe (exceto para o próprio automóvel)
        if (!automovel.getPlaca().equals(request.placa()) && 
            automovelRepository.existsByPlaca(request.placa())) {
            throw new IllegalStateException("Já existe um automóvel com esta placa");
        }

        Usuario proprietario = usuarioRepository.findById(request.proprietarioId())
                .orElseThrow(() -> new EntityNotFoundException("Proprietário não encontrado"));

        automovel.setMatricula(request.matricula());
        automovel.setPlaca(request.placa());
        automovel.setAno(request.ano());
        automovel.setMarca(request.marca());
        automovel.setModelo(request.modelo());
        automovel.setProprietario(proprietario);

        return automovelRepository.save(automovel);
    }

    public void deletarAutomovel(Long id) {
        if (!automovelRepository.existsById(id)) {
            throw new EntityNotFoundException("Automóvel não encontrado");
        }
        automovelRepository.deleteById(id);
    }
}