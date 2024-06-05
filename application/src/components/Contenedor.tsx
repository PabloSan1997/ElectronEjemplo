/* eslint-disable react-hooks/exhaustive-deps */
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { Tareas } from "./Tareas";
import '../styles/tareas.scss';
import { useEffect } from "react";
import { readTareaExtraReducer } from "../slices/extraReducer";


export function Contenedor() {
    const state = useAppSelector(state => state.tareaReducer);
    const distpatch = useAppDispatch();
    useEffect(()=>{
        distpatch(readTareaExtraReducer({token:state.token}));
    },[]);
    return (
        <div className="contenedor">
            {state.tareas.map(t => (
                <Tareas key={t.id} {...t} />
            ))}
        </div>
    );
}
