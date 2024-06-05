

export const convertirFecha=(fecha:string)=>{
    const date = new Date(fecha);
    return date.toLocaleString();
}