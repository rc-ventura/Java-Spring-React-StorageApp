import React from 'react'
import { useState, useEffect } from 'react'
import axios from "axios";
import {  Link } from "react-router-dom";



export default function ViewCategory() {

    //let navigate = useNavigate();

    const [categories, setCategories] = useState([

    ]);

    const [category, setCategory] =useState({

        
        nome: ''
    })

    const { nome } = category;

    const onInputChange = (e) => {
        setCategory({ ...category, [e.target.name]: e.target.value });

    };

    useEffect(() => {
        loadCategories();

    }, []);

    const loadCategories = async () => {

        const result = await axios.get("http://localhost:8080/categorias/")
        setCategories(result.data);
        console.log(result.data);
    }

    const deleteCategory = async (id) => {
        await axios.delete((`http://localhost:8080/categorias/${id}`))
        loadCategories();
    }

    const onSubmit = async (e) => {
    
        await axios.post("http://localhost:8080/categorias/", category)
        alert("Categoria cadastrada com sucesso");
    };

    return (


        <div className='container text-center'>
            <div className='row'>
                <div className='col-md-12 offset md-3 border rounded p-6 mt-2 shadow'>
                    <h2 className='text-center m-4'> Register Category</h2>
                    <br />
                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className='mb-3'>
                            <label htmlFor='name' className='form-label'>
                                <strong>Name</strong>
                            </label>
                            <input type={"text"} className="form-control" placeholder="Enter with category's name" name='nome'
                                value={nome} onChange={(e) => onInputChange(e)}
                            />


                        </div>
                        <button type='submit' className='btn btn-outline-primary'>Submit</button>
                        <Link className='btn btn-outline-danger mx-2' to="/">Cancel</Link>
                    </form>


                    <div className='container'>
                        <div className='py-5' >
                            <table className="table  text-center border shadow ">
                                <thead>
                                    <tr>
                                        <th scope="col ">#</th>

                                        <th scope="col">Name</th>
                                        <th></th>
                                        <th></th>

                                        <th scope="col">Action</th>


                                    </tr>
                                </thead>
                                <tbody>



                                   
                                {categories.map((categories, index) =>(

                                        
                                    <tr >
                                        <th scope="row " key = {index}   > {index + 1}</th>


                                        <td key={index + 1}>{categories.nome}</td>
                                        <td></td>
                                        <td></td>

                                        <Link className='btn btn-outline-primary mx-2 mb-2 mt-2' to={`/viewCategory/${categories.id}`} >View</Link>
                                        <Link className='btn btn-outline-primary mx-2 mb-2 mt-2' to={`/editCategory/${categories.id}`} >Edit</Link>
                                        <button className='btn btn-outline-danger mx-2 mb-2 mt-2' onClick={()=>deleteCategory(categories.id)} >Delete</button>
                                    

                                    </tr>

                                ))
}


                                </tbody>
                            </table>
                        </div>

                    </div>




                </div>

            </div>

        </div>


    );
}
