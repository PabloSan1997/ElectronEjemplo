import { XCircleIcon, CheckCircleIcon } from '@heroicons/react/24/solid';
import { useAppDispatch, useAppSelector } from '../store/hooks';
import { deleteTareaExtraReducer, pathcTareaExtraReducer } from '../slices/extraReducer';
import { convertirFecha } from '../urilities/covertirFecha';


export function Tareas({tarea, estado, createAt, id}:TareaInterface) {
    const dispatch = useAppDispatch();
    const token = useAppSelector(state => state.tareaReducer.token);
    const editar = () => {
      dispatch(pathcTareaExtraReducer({id, tarea:{estado:!estado}, token}));
    }
    const deleteData = () =>{
      dispatch(deleteTareaExtraReducer({id, token}));
    }
  return (
    <div className={estado?'tarea hecha':'tarea'}>
        <div className="area_buttons">
            <CheckCircleIcon onClick={editar} className='ico'/>
            <XCircleIcon onClick={deleteData} className='ico'/>
        </div>
        <p className="texto">{tarea}</p>
        <span className="create">{convertirFecha(createAt)}</span>
    </div>
  );
}
