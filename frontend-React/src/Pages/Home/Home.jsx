import React, {useEffect, useState} from 'react'
import axios from "axios";
import  { Link } from "react-router-dom";



export default function Home() {

    const [product, setProduct] =useState([]);

    


    useEffect(() => {
        loadProducts();

    }, []);

    const loadProducts =async() => {

        const result= await axios.get("http://localhost:8080/produtos/")
        setProduct(result.data);
        console.log(result.data);
    }

    const deleteProduct = async (id) => {
        await axios.delete((`http://localhost:8080/produtos/${id}`))
        loadProducts()
    }

   

    

    return (
        <div className='container'>
            <div className='py-4' >
                <table className="table text-center border shadow ">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Logo</th>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Categorias</th>
                            <th scope="col">Action</th>


                        </tr>
                    </thead>
                    <tbody>

                            {   
                                
                                product.map((product)=>(

                                    <tr>
                                    <th scope="row" key ={product.id}> {product.id}</th>
                                    
                                    
                                    <td ><img src={`data:${product.fileStorage_type};base64,${product.fileStorage_data}`} alt= "foto do produto" width={70} height={70}></img></td>
                                            
                                    <td>{product.nome}</td>
                                    <td>{product.preco}</td>
                                    <td>{product.qtd}</td>

                                   <td>{product.categoria.nome} </td>

                                    <td>  
                                        <Link className='btn btn-primary mx-2' to={`/viewProduct/${product.id}`}>View</Link>
                                        <Link className='btn btn-outline-primary mx-2' to={`/editProduct/${product.id}`}>Edit</Link>
                                        <button className='btn btn-danger mx-2' onClick={()=>deleteProduct(product.id)}>Delete</button>

                                    </td>

                                    </tr>


                                ))
                            }
                        
                    </tbody>
                </table>
            </div>

        </div>
    )
}
