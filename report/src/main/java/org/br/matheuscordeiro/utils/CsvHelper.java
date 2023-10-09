package org.br.matheuscordeiro.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.br.matheuscordeiro.dto.OpportunityDto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CsvHelper {
    public static ByteArrayInputStream opportunitiesToCsv(List<OpportunityDto> opportunityDtoList) {
        final var format =
                CSVFormat.DEFAULT.withHeader("Id Proposal, Customer, Price for Tonne", "Last Currency quotation");
        try {
            final var out = new ByteArrayOutputStream();
            final var csvPrinter = new CSVPrinter(new PrintWriter(out), format);
            for (OpportunityDto opportunityDto : opportunityDtoList) {
                final var data = Arrays.asList(String.valueOf(opportunityDto.proposalId()),
                        String.valueOf(opportunityDto.customer()), String.valueOf(opportunityDto.priceTonne()),
                        String.valueOf(opportunityDto.lastCurrencyQuotation()));
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to import data to CSV file: " + e.getMessage());
        }
    }
}
