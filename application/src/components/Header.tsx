
import '../styles/header.scss';
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { tareaActions } from '../slices/tareaSlice';

export function Header() {
    const state = useAppSelector(state => state.tareaReducer);
    const checarToken = !!state.token;
    const total = state.totales;
    const hechas = state.hechas;
    const dispatch = useAppDispatch();
    return (
        <header>
            <h1>Mis tareas</h1>
            <button onClick={()=>dispatch(tareaActions.logout())}>Logout</button>
            {checarToken ? <span>Tareas: {hechas}/{total}</span> : <></>}
        </header>
    );
}
