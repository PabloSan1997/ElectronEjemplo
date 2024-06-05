
const contentType = {
    'Content-Type':'application/json'
}

const headerToken =(token:string)=> ({
    ...contentType,
    'Authorization':`Bearer ${token}`
});


export class ReadApi{
    private url = import.meta.env.DEV?'http://localhost:3007':'https://tareas-servicios.onrender.com';
    async login(log:LoginRequest):Promise<LoginResponse>{
        const ft = await fetch(`${this.url}/login`,{
            method:'POST',
            headers:{
                ...contentType
            },
            body:JSON.stringify(log)
        });
        if(!ft.ok) throw await ft.json();
        return ft.json();
    }
    async readTareas(token:string):Promise<TareaInterface[]>{
        const ft = await fetch(`${this.url}/api/tareas`,{
            method:'GET',
            headers:{
                ...headerToken(token)
            }
        });
        if(!ft.ok) throw await ft.json();
        return ft.json();
    }
    async patchTareas(token:string, id:number, datos:EditarEstado):Promise<TareaInterface>{
        const ft = await fetch(`${this.url}/api/tareas/${id}`,{
            method:'PATCH',
            headers:{
                ...headerToken(token)
            },
            body:JSON.stringify(datos)
        });
        if(!ft.ok) throw await ft.json();
        return ft.json();
    }
    async postTareas(token:string, datos:PostTarea):Promise<TareaInterface>{
        const ft = await fetch(`${this.url}/api/tareas`,{
            method:'POST',
            headers:{
                ...headerToken(token)
            },
            body:JSON.stringify(datos)
        });
        if(!ft.ok) throw await ft.json();
        return ft.json();
    }
    async deleteTarea(token:string, id:number):Promise<{id:number}>{
        const ft = await fetch(`${this.url}/api/tareas/${id}`,{
            method:'DELETE',
            headers:{
                ...headerToken(token)
            }
        });
        if(!ft.ok) throw await ft.json();
        return {id};
    }
}