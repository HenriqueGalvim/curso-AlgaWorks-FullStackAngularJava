package com.example.algamoney.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoney.model.Lancamento;
import com.example.algamoney.model.Pessoa;
import com.example.algamoney.repository.PessoaRepository;
import com.example.algamoney.repository.LancamentoRepository;

import com.example.algamoney.service.exception.PessoaInexistenteOuInativaException;


@Service
public class LancamentoService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public Lancamento saveLancamentoService(Lancamento lancamento) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());

        if (!pessoa.isPresent() || !pessoa.get().getAtivo()) {
	        throw new PessoaInexistenteOuInativaException();
	    }
        return lancamentoRepository.save(lancamento);
	}
}
