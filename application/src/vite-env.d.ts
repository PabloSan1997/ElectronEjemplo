/// <reference types="vite/client" />


interface InitialState {
    token:string,
    tareas:TareaInterface[],
    username:string;
    totales:number;
    hechas:number;
    message:string;
}


interface PostTarea{
    tarea:string;
}

interface TareaInterface extends PostTarea{
    id:number;
    estado:boolean;
    createAt:string;
}

interface EditarEstado{
    estado:boolean;
}

interface LoginRequest{
    username:string;
    password:string;
}

interface LoginResponse{
    token:string;
    username:string;
}

interface ErrorDto{
    statusCode:number;
    message:string;
    error:string;
}