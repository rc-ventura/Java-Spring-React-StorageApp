import React from 'react'
import { useState } from 'react'
import axios from "axios";
import  {useNavigate, Link} from "react-router-dom";

export default function AddCategory() {

    let navigate = useNavigate();

    const [category,setCategory]=useState({
            
        nome: ""
    });

    const{nome} = category;

    const onInputChange=(e)=>{
        setCategory({...category, [e.target.name]: e.target.value});

    };

    const onSubmit= async (e)=> {
        e.preventDefault();
         await axios.post("http://localhost:8080/categorias/", category)
         navigate("/");
         alert("Categoria cadastrada com sucesso");
    };

    return (


        <div className='container text-center'>
            <div className='row'>
                <div className='col-md-6 offset md-3 border rounded p-4 mt-2 shadow'>
                    <h2 className='text-center m-4'> Register Category</h2>
                    <br />
                   <form onSubmit={(e) => onSubmit(e)}>
                    <div className='mb-3'>
                        <label htmlFor='name' className='form-label'>
                            <strong>Name</strong>
                        </label>
                        <input type={"text"} className="form-control" placeholder="Enter with category's name" name='nome'
                         value={nome} onChange={(e)=>onInputChange(e)}
                        />


                    </div>
                    <button type='submit' className='btn btn-outline-primary'>Submit</button>
                    <Link className='btn btn-outline-danger mx-2' to="/">Cancel</Link>
                    </form>
                   

                   
                </div>
            </div>
        </div>
           
    );
}
