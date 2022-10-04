package br.com.insurance.challenge.insurance.controller;

import br.com.insurance.challenge.insurance.entity.dto.ApoliceDto;
import br.com.insurance.challenge.insurance.entity.dto.PagamentoDto;
import br.com.insurance.challenge.insurance.entity.dto.ParcelaDto;
import br.com.insurance.challenge.insurance.service.ApoliceService;
import br.com.insurance.challenge.insurance.service.PaymentService;
import br.com.insurance.challenge.insurance.util.ConvertDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@RestController
@RequestMapping(path = "/apolice")
public class ApoliceController {

    @Autowired
    ApoliceService apoliceService;

    @Autowired
    PaymentService paymentService;

    @RequestMapping(path = "/include", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void includePolicy(@RequestBody ApoliceDto apoliceDto) throws ParseException {
        apoliceService.savePolicyService(apoliceDto);
    }

    @RequestMapping(path = "/alter", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void alterPolicy(@RequestBody ApoliceDto apoliceDto) throws ParseException {
        apoliceDto.setDataAlteracaoRegistro(ConvertDate.convertDateToString(Date.from(LocalDate.now()
                .atStartOfDay(ZoneId.systemDefault()).toInstant())));
        for (ParcelaDto parcelaDto : apoliceDto.getParcelas()) {
            parcelaDto.setDataAlteracaoRegistro(ConvertDate.convertDateToString(Date.from(LocalDate.now()
                    .atStartOfDay(ZoneId.systemDefault()).toInstant())));
        }
        apoliceService.savePolicyService(apoliceDto);
    }

    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> findAllPolicy() {
        return ResponseEntity.status(HttpStatus.OK).body(apoliceService.findPolicyService());
    }

    @RequestMapping(path = "/payment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void payment(@RequestBody PagamentoDto pagamentoDto) throws ParseException {
        paymentService.calculatePayment(pagamentoDto);
    }
}
