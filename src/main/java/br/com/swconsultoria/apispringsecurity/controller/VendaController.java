package br.com.swconsultoria.apispringsecurity.controller;

import br.com.swconsultoria.apispringsecurity.entity.Venda;
import br.com.swconsultoria.apispringsecurity.service.VendaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/venda")
@AllArgsConstructor
public class VendaController {

    private final VendaService vendaService;

    @PostMapping
    public Venda salvar(@RequestBody Venda venda){
        return vendaService.salvar(venda);
    }

    @GetMapping("{cnpj}")
    public List<Venda> buscarPorCnpj(@PathVariable("cnpj") String cnpj){
        return vendaService.buscarPorCnpj(cnpj);
    }
}
