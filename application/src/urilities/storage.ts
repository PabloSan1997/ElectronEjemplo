

export const myStorage = {
    read:():LoginResponse=>{
        if(!localStorage.datos){
            localStorage.datos = JSON.stringify({username:'', token:''})
        }
        return JSON.parse(localStorage.datos);
    },
    save:(data:LoginResponse)=>{
        localStorage.datos = JSON.stringify(data);
    }
}