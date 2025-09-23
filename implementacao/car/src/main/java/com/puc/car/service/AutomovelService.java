package com.puc.car.service;

import com.puc.car.models.Automovel;
import com.puc.car.repositories.AutomovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class AutomovelService {

    @Autowired
    private AutomovelRepository automovelRepository;

    public Automovel criarAutomovel(Automovel automovel) {
        // Validações de negócio
        if (automovelRepository.existsByMatricula(automovel.getMatricula())) {
            throw new RuntimeException("Matrícula já cadastrada");
        }
        if (automovelRepository.existsByPlaca(automovel.getPlaca())) {
            throw new RuntimeException("Placa já cadastrada");
        }
        
        return automovelRepository.save(automovel);
    }

    public Automovel buscarPorId(UUID id) {
        return automovelRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Automóvel não encontrado"));
    }

    public List<Automovel> listarTodos() {
        return automovelRepository.findAll();
    }

    public Automovel buscarPorMatricula(String matricula) {
        return automovelRepository.findByMatricula(matricula)
            .orElseThrow(() -> new RuntimeException("Automóvel não encontrado"));
    }

    public Automovel buscarPorPlaca(String placa) {
        return automovelRepository.findByPlaca(placa)
            .orElseThrow(() -> new RuntimeException("Automóvel não encontrado"));
    }

    public List<Automovel> buscarPorMarca(String marca) {
        return automovelRepository.findByMarca(marca);
    }

    public List<Automovel> buscarPorModelo(String modelo) {
        return automovelRepository.findByModelo(modelo);
    }

    public List<Automovel> buscarPorProprietario(String proprietario) {
        return automovelRepository.findByProprietario(proprietario);
    }

    public List<Automovel> buscarPorMarcaEModelo(String marca, String modelo) {
        return automovelRepository.findByMarcaAndModelo(marca, modelo);
    }

    public Automovel atualizarAutomovel(UUID id, Automovel automovelAtualizado) {
        Automovel automovel = buscarPorId(id);
        automovel.setMarca(automovelAtualizado.getMarca());
        automovel.setModelo(automovelAtualizado.getModelo());
        automovel.setAno(automovelAtualizado.getAno());
        automovel.setProprietario(automovelAtualizado.getProprietario());
        return automovelRepository.save(automovel);
    }

    public void removerAutomovel(UUID id) {
        if (!automovelRepository.existsById(id)) {
            throw new RuntimeException("Automóvel não encontrado");
        }
        automovelRepository.deleteById(id);
    }
}