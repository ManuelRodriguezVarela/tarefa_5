package gal.san.clemente.tarefa_5_bdo.model.converter;

import java.util.List;

public interface IConverter<C, E> {
    List<E> converterKToE(C from);
}
