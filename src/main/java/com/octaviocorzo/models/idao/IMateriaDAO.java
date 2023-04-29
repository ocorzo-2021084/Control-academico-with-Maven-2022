package com.octaviocorzo.models.idao;

import com.octaviocorzo.models.domain.Materia;

/**
 *
 * @author informatica
 */
import com.octaviocorzo.models.domain.Materia;
import java.util.List;
import javafx.collections.ObservableList;

public interface IMateriaDAO {

    public ObservableList<Materia> getAll();

    public boolean add(Materia materia);

    public boolean update(Materia materia);

    public boolean delete(int idMateria);

}
