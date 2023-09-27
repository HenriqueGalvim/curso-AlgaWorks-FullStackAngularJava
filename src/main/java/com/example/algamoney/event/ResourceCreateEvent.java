package com.example.algamoney.event;

import org.springframework.context.ApplicationEvent;

import jakarta.servlet.http.HttpServletResponse;

public class ResourceCreateEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private Long codigo;
	
	public ResourceCreateEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);
		// Passa o mouse sobre o vermelho e clique Control + 1,ele dará algumas sugestões para a correção do erro
		this.response = response;
		this.codigo = codigo;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getCodigo() {
		return codigo;
	}
}
