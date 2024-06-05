import { createSlice } from "@reduxjs/toolkit";
import { addTareaExtraReducer, deleteTareaExtraReducer, loginExtraReducer, pathcTareaExtraReducer, readTareaExtraReducer } from "./extraReducer";
import { myStorage } from "../urilities/storage";


const initialState: InitialState = {
    token: myStorage.read().token,
    tareas: [],
    username: myStorage.read().username,
    totales: 0,
    hechas: 0,
    message: ""
}

const tareaSlice = createSlice({
    name: 'slice/tarea',
    initialState,
    reducers: {
        logout: (state) => {
            state.tareas = [];
            state.username = '';
            state.token = '';
            state.message = '';
            myStorage.save({ username: '', token: '' });
        }
    },
    extraReducers: (builder) => {
        builder.addCase(loginExtraReducer.fulfilled, (state, action) => {
            state.token = action.payload.token;
            state.username = action.payload.username;
            myStorage.save({ username: action.payload.username, token: action.payload.token });
            state.message = '';
            console.log(action.payload);
        });
        builder.addCase(loginExtraReducer.rejected, (state, action) => {
            state.token = "";
            state.username = "";
            state.message = action.error.message as string;
            myStorage.save({ username: '', token: '' });
        });

        builder.addCase(readTareaExtraReducer.fulfilled, (state, action) => {
            state.tareas = action.payload;
        });
        builder.addCase(readTareaExtraReducer.rejected, (state) => {
            state.tareas = [];
            state.tareas = [];
            state.username = '';
            state.token = '';
            myStorage.save({ username: '', token: '' });
            state.totales = 0;
            state.hechas = 0;
        });

        builder.addCase(addTareaExtraReducer.fulfilled, (state, action)=>{
            state.tareas = [...state.tareas, action.payload];
            state.message = '';
        });
        builder.addCase(addTareaExtraReducer.rejected, (state,action)=>{
            state.message = action.error.message as string;
        });

        builder.addCase(pathcTareaExtraReducer.fulfilled, (state, action)=>{
            const id = action.payload.id;
            const index = state.tareas.findIndex(t => t.id===id);
            state.tareas[index] = action.payload;
        });

        builder.addCase(deleteTareaExtraReducer.fulfilled, (state, action)=>{
            const id = action.payload.id;
            state.tareas = state.tareas.filter(t => t.id!==id);
        })
    }
});

export const tareaReducer = tareaSlice.reducer;
export const tareaActions = tareaSlice.actions;