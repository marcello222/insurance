package br.com.insurance.challenge.insurance.thread.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThreadQeue {

    private String name;
    private String email;
}
