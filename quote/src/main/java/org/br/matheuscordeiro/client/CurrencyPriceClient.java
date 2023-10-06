package org.br.matheuscordeiro.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.br.matheuscordeiro.dto.CurrencyPriceDto;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/last")
@RegisterRestClient
@ApplicationScoped
public interface CurrencyPriceClient {
    @GET
    @Path("/{pair}")
    CurrencyPriceDto getPriceByPair(@PathParam("pair") String string);
}
