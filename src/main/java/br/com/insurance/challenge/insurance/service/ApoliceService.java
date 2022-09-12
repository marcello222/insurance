package br.com.insurance.challenge.insurance.service;

import br.com.insurance.challenge.insurance.entity.Apolice;
import br.com.insurance.challenge.insurance.entity.Parcela;
import br.com.insurance.challenge.insurance.entity.dto.ApoliceDto;
import br.com.insurance.challenge.insurance.entity.dto.ParcelaDto;
import br.com.insurance.challenge.insurance.repository.ApoliceRepository;
import br.com.insurance.challenge.insurance.util.ConvertDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ApoliceService {

    @Autowired
    ApoliceRepository apoliceRepository;

    ConvertDate convertDate = new ConvertDate();

    public void savePolicyService(ApoliceDto apoliceDto) throws ParseException {

        Apolice apolice = new Apolice();
        apolice.setId(apoliceDto.getId());
        apolice.setCpf(apoliceDto.getCpf());
        apolice.setDescricao(apoliceDto.getDescricao());
        apolice.setPremioTotal(apoliceDto.getPremioTotal());
        apolice.setSituacao(apoliceDto.getSituacao());
        apolice.setUsuarioCriacaoRegistro(apoliceDto.getUsuarioCriacaoRegistro());
        apolice.setDataCriacaoRegistro(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        apolice.setUsuarioAlteracaoRegistro(apoliceDto.getUsuarioAlteracaoRegistro());
        apolice.setDataAlteracaoRegistro(ConvertDate.convertStringToDate(apoliceDto.getDataAlteracaoRegistro()));

        List<Parcela> parcelaList = new ArrayList<>();

        for (ParcelaDto parcelaDto : apoliceDto.getParcelas()) {

            Parcela parcela = new Parcela();
            parcela.setId(parcelaDto.getId());
            parcela.setDataPagamento(ConvertDate.convertStringToDate(parcelaDto.getDataPagamento()));
            parcela.setDataPago(ConvertDate.convertStringToDate(parcelaDto.getDataPago()));
            parcela.setFormaPagamento(parcelaDto.getFormaPagamento());
            parcela.setJuros(parcelaDto.getJuros());
            parcela.setUsuarioAlteracaoRegistro(parcela.getUsuarioAlteracaoRegistro());
            parcela.setUsuarioCriacaoRegistro(parcelaDto.getUsuarioCriacaoRegistro());
            parcela.setPremio(parcelaDto.getPremio());
            parcela.setSituacao(parcelaDto.getSituacao());
            parcela.setDataCriacaoRegistro(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            parcela.setDataAlteracaoRegistro(ConvertDate.convertStringToDate(parcelaDto.getDataAlteracaoRegistro()));
            parcelaList.add(parcela);
        }

        apolice.setParcelas(parcelaList);

        apoliceRepository.save(apolice);
    }

    public List<Apolice> findPolicyService() {
        return (List<Apolice>) apoliceRepository.findAll();
    }
}

