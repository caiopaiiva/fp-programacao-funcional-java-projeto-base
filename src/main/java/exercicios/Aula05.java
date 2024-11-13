package exercicios;

import exercicios.base.Aula;

public class Aula05 extends Aula {
    public Aula05() {
        double menorNota = estudantes.stream()
                .filter(Estudante::isHomem)
                .filter(Estudante::hasNota)
                .mapToDouble(Estudante::getNota)
                .map(nota -> nota*100)
                .min()
                .orElse(0);
        System.out.println(menorNota);
    }
    public static void main(String[] args) {
        new Aula05();
    }
}
