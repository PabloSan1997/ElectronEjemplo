
import React, { useState } from 'react';
import '../styles/addForm.scss';
import { useAppDispatch, useAppSelector } from '../store/hooks';
import { addTareaExtraReducer } from '../slices/extraReducer';

export function Addform() {
  const [tarea, setTarea] = useState('');
  const dispatch = useAppDispatch();
  const token = useAppSelector(state => state.tareaReducer.token);
  const message = useAppSelector(state => state.tareaReducer.message);
  const submit=(e:React.FormEvent<HTMLFormElement>)=>{
    e.preventDefault();
    dispatch(addTareaExtraReducer({tarea:{tarea}, token}));
    setTarea('');
  }
  return (
    <form className='addFormulario' onSubmit={submit}>
      <label htmlFor="escribir">Agregar tarea</label>
      <textarea
        id="escribir"
        placeholder="Escribir..."
        rows={3}
        value={tarea}
        onChange={e => setTarea(e.target.value)}
      ></textarea>
      {message?<p className="error">{message}</p>:null}
      <button type="submit">Agregar</button>
    </form>
  );
}
