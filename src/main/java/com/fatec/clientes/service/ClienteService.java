package com.fatec.clientes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.clientes.model.Cliente;
import com.fatec.clientes.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente inserir(Cliente cliente) {
        cliente.setId(0); // garante que será um INSERT
        return repository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    public Optional<Cliente> buscarPorId(long id) {
        return repository.findById(id);
    }

    public Optional<Cliente> atualizar(long id, Cliente dados) {
        return repository.findById(id).map(existente -> {
            existente.setNome(dados.getNome());
            existente.setCnpj(dados.getCnpj());
            existente.setEndereco(dados.getEndereco());
            existente.setTelefone(dados.getTelefone());
            existente.setEmail(dados.getEmail());
            existente.setFormapagto(dados.getFormapagto());
            existente.setStatus(dados.getStatus());
            return repository.save(existente);
        });
    }

    public boolean deletar(long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}