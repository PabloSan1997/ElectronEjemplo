import { Navigate } from 'react-router-dom';
import { useAppDispatch, useAppSelector } from '../store/hooks';
import '../styles/login.scss';
import { routesList } from '../urilities/routes';
import { useState } from 'react';
import { loginExtraReducer } from '../slices/extraReducer';

export function Login() {
    const [texto, setTexto] = useState<LoginRequest>({ username: '', password: '' });
    const state = useAppSelector(state => state.tareaReducer);
    const dispatch = useAppDispatch();
    const viewToken = !!state.token;
    const submit=(e:React.FormEvent<HTMLFormElement>)=>{
        e.preventDefault();
        dispatch(loginExtraReducer(texto));
    }
    if (viewToken) return <Navigate to={routesList.home} />
    return (
        <form className="login" onSubmit={submit}>
            <h2>Entrar</h2>
            <input
                value={texto.username}
                onChange={e => setTexto({ ...texto, username: e.target.value })}
                type="text"
                placeholder="Username"
            />
            <input
                value={texto.password}
                onChange={e => setTexto({ ...texto, password: e.target.value })}
                type="password"
                placeholder="Password"
            />
            <button type="submit">Entrar</button>
            {state.message?<p className="error">{state.message}</p>:null}
        </form>
    );
}
