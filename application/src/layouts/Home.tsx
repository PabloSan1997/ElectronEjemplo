
import { useAppSelector } from "../store/hooks";
import { Navigate } from "react-router-dom";
import { routesList } from "../urilities/routes";
import '../styles/home.scss';
import { Addform } from "../components/Addform";
import { Contenedor } from "../components/Contenedor";

export function Home() {
    const state = useAppSelector(state => state.tareaReducer);
    if (!state.token) return <Navigate to={routesList.login} />
    return (
        <>
            <h2 className="username">Tareas de {state.username}</h2>
            <Addform />
            <Contenedor/>
        </>
    );
}
