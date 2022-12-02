import React from 'react'
import axios from "axios";
import { useState } from 'react'
import  {useNavigate, Link, useParams} from "react-router-dom";
import { useEffect } from 'react';


export default function EditProduct () {
  

let navigate = useNavigate();

const {id} =useParams();

const [product,setProduct]=useState({
        
    nome: "",
    preco: "",
    qtd: "",
    categoria: "",
});



const{nome,preco,qtd} = product;

const [categorias, setCategorias] = useState([]);






const handleCategory = (e) => {
    setProduct ({...product, categoria: {

        id: e.target.value,
        nome: e.target.options[e.target.selectedIndex].text
    }
    
    })
} 

const loadCategorias=async() => {
    const result = await axios.get("http://localhost:8080/categorias")
    setCategorias(result.data);
    console.log(result.data);


}

const onInputChange=(e)=>{
    setProduct({...product, [e.target.name]: e.target.value});

};

useEffect(()=>{

    loadProducts();
    loadCategorias();
},

[]);


const onSubmit= async (e)=> {
    e.preventDefault();
     await axios.put(`http://localhost:8080/produtos/${id}`, product)
     navigate("/");
     alert("Produto editado com sucesso");
};


const loadProducts =async() => {

    const result= await axios.get(`http://localhost:8080/produtos/${id}`)
    setProduct(result.data);


}
return (


    <div className='container text-center'>
        <div className='row'>
            <div className='col-md-6 offset md-3 border rounded p-4 mt-1 shadow'>
                <h2 className='text-center m-4'> Edit Product</h2>
                <br />
               <form   onSubmit={(e) => onSubmit(e)}>
                <div className='mb-3'>
                    <label htmlFor='name' className='form-label'>
                        <strong>Name</strong>
                    </label>
                    <input type={"text"} className="form-control" placeholder='Enter with product name' name='nome'
                     value={nome} onChange={(e)=>onInputChange(e)}
                    />

                </div>
                <div className='mb-4'>
                    <label htmlFor='price' className='form-label'>
                        <strong>Price</strong>
                    </label>
                    <input type={"number"} className="form-control" placeholder='Enter with price' name='preco'
                     value={preco} onChange={(e)=>onInputChange(e)}
                    />

                </div>
                <div className='mb-4'>
                    <label htmlFor='qtd' className='form-label'>
                        <strong>Quantity</strong>
                    </label>
                    <input type={"number"} className="form-control" placeholder='Enter with quantity' name='qtd' 
                    value={qtd} onChange={(e)=>onInputChange(e)}
                    />
                    <br></br>
                        <div className='mb-4 '>
                        <label htmlFor='categoria' className='form-label' >
                                <strong>Categoria</strong>
                            </label>
                            <select onChange={(e) => handleCategory(e)} className="form-select" aria-label='Default select example'   name='categoria'>
                                <option  selected> {product.categoria.nome}</option>
                                { categorias.map((categoria) =>(
                                    <option value= {categoria.id}>{categoria.nome}</option>  
                                 ))


                                }

                                </select>
                                </div>
                                
                </div>
                <div className='mb-4'>
                    <label htmlFor='file' className='form-label'>
                        <strong>Logo</strong>

                    </label>
                    <input type={"text"} className="form-control" placeholder='Enter with your image' name='file'
                     value={null} onChange={(e)=>onInputChange(e)}
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
