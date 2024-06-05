import { createAsyncThunk } from "@reduxjs/toolkit";
import { ReadApi } from "../api/ReadApi";


const readApi = new ReadApi();

export const loginExtraReducer = createAsyncThunk(
    'extraReducer/login',
    async ( data:LoginRequest) => {
        try {
            return readApi.login(data);
        } catch (error) {
            const err = error as ErrorDto;
            throw err.message ? err : { message: 'Error' };
        }
    }
);

export const readTareaExtraReducer = createAsyncThunk(
    'extraReducer/readTarea',
    async ({ token }: { token: string }) => {
        try {
            return readApi.readTareas(token);
        } catch (error) {
            const err = error as ErrorDto;
            throw err.message ? err : { message: 'Error' };
        }
    }
);

export const addTareaExtraReducer = createAsyncThunk(
    'extraReducer/addTarea',
    async ({ token, tarea }: { token: string, tarea: PostTarea}) => {
        try {
            return readApi.postTareas(token, tarea);
        } catch (error) {
            const err = error as ErrorDto;
            throw err.message ? err : { message: 'Error' };
        }
    }
);

export const pathcTareaExtraReducer = createAsyncThunk(
    'extraReducer/editTarea',
    async ({ token, tarea, id }: { token: string, tarea: EditarEstado, id:number }) => {
        try {
            return readApi.patchTareas(token, id, tarea);
        } catch (error) {
            const err = error as ErrorDto;
            throw err.message ? err : { message: 'Error' };
        }
    }
);
export const deleteTareaExtraReducer = createAsyncThunk(
    'extraReducer/deleteTarea',
    async ({ token, id }: { token: string, id:number }) => {
        try {
            return readApi.deleteTarea(token, id);
        } catch (error) {
            const err = error as ErrorDto;
            throw err.message ? err : { message: 'Error' };
        }
    }
);