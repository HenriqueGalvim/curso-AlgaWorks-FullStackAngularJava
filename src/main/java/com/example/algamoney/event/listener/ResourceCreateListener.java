package com.example.algamoney.event.listener;

import java.net.URI;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoney.event.ResourceCreateEvent;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class ResourceCreateListener implements ApplicationListener<ResourceCreateEvent> {

	@Override
	public void onApplicationEvent(ResourceCreateEvent resourceCreateEvent) {
		HttpServletResponse response = resourceCreateEvent.getResponse();
		Long codigo = resourceCreateEvent.getCodigo();
		
		adicionarHeaderLocation(response, codigo);
	}

	private void adicionarHeaderLocation(HttpServletResponse response, Long codigo) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(codigo).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}
	
}
