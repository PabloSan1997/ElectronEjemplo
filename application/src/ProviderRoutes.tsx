
import { HashRouter, Navigate, useRoutes } from 'react-router-dom';
import { routesList } from './urilities/routes';
import { useAppSelector } from './store/hooks';
import { Header } from './components/Header';
import { Login } from './layouts/Login';
import { Home } from './layouts/Home';

const Redirect = () => {
    const state = useAppSelector(state => state.tareaReducer);
    if (!state.token)
        return <Navigate to={routesList.login} />
    
    return <Navigate to={routesList.home}/>
}

const Routes = () => useRoutes([
    {
        path: routesList.login,
        element: <Login/>
    },
    {
        path: routesList.home,
        element: <Home/>
    },
    {
        path: '/',
        element: <Redirect />
    }
]);

export function ProviderRoutes() {
    return (
        <HashRouter>
            <Header/>
            <Routes />
        </HashRouter>
    );
}
