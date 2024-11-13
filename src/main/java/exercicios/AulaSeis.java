package exercicios;

import exercicios.base.Aula;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingDouble;
//import static java.util.function.Predicate.not;

public class AulaSeis extends Aula {
    public AulaSeis() {
        /*var filtroCast = ((Predicate<Estudante>) Estudante::isHomem).and(Estudante::hasCurso);
        Predicate<Estudante> filtro = not(Estudante::hasCurso);
        long estudanteSemCurso = estudantes.stream()
                .filter(Predicate.not(filtro))*/

        var filtro = ((Predicate<Estudante>) Estudante::hasCurso).and(Estudante::isAprovado);
        var notaComparador = comparingDouble(Estudante::getNota).reversed();
        var comparator = comparing(Estudante::getCurso).thenComparing(notaComparador);
        estudantes.stream()
                .filter(filtro)
                .sorted(comparator);
                //.forEach(System.out::println);

        //Map<Curso, List<Estudante>> novosEstudantes = estudantes.stream()
        //var novosEstudantes = estudantes.stream()
        var mediasNotas = estudantes.stream()
                .filter(Estudante::hasCurso)
                .filter(Estudante::isMulher)
                //.collect(Collectors.groupingBy(Estudante::getCurso));
                .collect(Collectors.groupingBy(Estudante::getCurso, Collectors.averagingDouble(Estudante::getNota)));
        //novosEstudantes.forEach(this::imprimeEstudantesCurso);
        mediasNotas.forEach((curso, mediaNotas) -> System.out.printf("%s: %.2f\n",curso.getNome(), mediaNotas));
    }
    public static void main(String[] args) {
        new AulaSeis();
    }

    private void imprimeEstudantesCurso(Curso curso, List<Estudante> estudantes) {
        System.out.println(curso.getNome());
        estudantes.forEach(e -> System.out.printf("\t%s\n",e.getNome()));
    }
}
