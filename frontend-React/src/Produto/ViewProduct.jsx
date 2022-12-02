import React from 'react'
import  { Link, useParams} from "react-router-dom";
import { useState } from 'react'
import { useEffect } from 'react';
import axios from "axios";





export default function ViewProduct() {
   
    const [product,setProduct]=useState({
            
        nome: "",
        preco: "",
        qtd: "",
        categoria:"",
        image: ""
    });
   
    

    const {id} =useParams();


    useEffect(() => {
        loadProducts();

    }, []);


    const loadProducts =async() => {

        const result= await axios.get(`http://localhost:8080/produtos/${id}`)
        setProduct(result.data);
        console.log(result.data);
    }
    
   
    return (

        <div className='container text-center'>
            <div className='row'>
                <div className='col-md-6 offset md-3 border rounded p-4 mt-2 shadow'>
                    <h2 className='text-center m-4' > Product Details</h2>
                    <div className='card'>
                        <div className='card-header'> Details of product
                            <ul className='list-group list-group-flush'>
                                
                                <li className='list-group-item'>
                                    <b> Id:</b>
                                    {product.id}  </li>
                                
                                <li className='list-group-item'>
                                    <b> Logo:</b>
                                    {product.image}  </li>


                                <li className='list-group-item'>
                                    <b> Name:</b> 
                                    {product.nome}   </li>



                                <li className='list-group-item'>
                                    <b> Price:</b>   
                                    {product.preco}  </li>


                                <li className='list-group-item'>
                                    <b> Quantity:</b> 
                                    {product.qtd} </li>
                                
                                <li className='list-group-item'>
                                    <b> Categoria:</b>
                                    {product.categoria.nome}  </li>



                            </ul>
                        </div>
                    </div>
                    <Link className='btn btn-primary my-2' to={"/"}> Back to Home</Link>
                </div>
            </div>
        </div>
    );
}

