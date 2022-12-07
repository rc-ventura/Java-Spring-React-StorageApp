import React from 'react'
import { useState, useEffect } from 'react'
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import Upload from '../Components/Upload/upload';




export default function AddProduct() {

    let navigate = useNavigate();

    const [product, setProduct] = useState({

        nome: "",
        preco: "",
        qtd: "",
        categoria: [],
        fileStorage: [],
        id: "",
        arquivo: undefined,



    });




    const [categorias, setCategorias] = useState([]);

    useEffect(() => {
        loadCategorias();
    }, []);


    const loadCategorias = async () => {
        const result = await axios.get("http://localhost:8080/categorias")
        setCategorias(result.data);
        console.log(result.data);


    }




    const { nome, preco, qtd } = product;

    const onInputChange = (e) => {
        setProduct({ ...product, [e.target.name]: e.target.value });

    };

    const handleCategory = (e) => {
        setProduct({
            ...product, categoria: {

                id: e.target.value,
                nome: e.target.options[e.target.selectedIndex].text
            }

        })
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        if (document.getElementById("arquivo").value === "") {
            alert("Favor adicionar um arquivo");
        } else {

         const data = await axios.get("http://localhost:8080/returnFiles/"+ document.getElementById("arquivo").value, product);

        const dados = {
        fileStorage: data.data,
        nome: e.target.elements.nome.value,
        preco: e.target.elements.preco.value,
        qtd: e.target.elements.qtd.value,
        categoria: product.categoria

        
      };
        await axios.post("http://localhost:8080/produtos/", dados)
        navigate("/");
        alert("Produto cadastrado com sucesso");
    };
    }


    return (


        <div className='container text-center'>
            <div className='row'>
                <div className='col-md-6 offset md-3 border rounded p-1 mt-2 shadow'>
                    <h2 className='text-center m-3'> Register Product</h2>
                    <br />
                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className='mb-2'>
                            <label htmlFor='name' className='form-label'>
                                <strong>Name</strong>
                            </label>
                            <input type={"text"} className="form-control" placeholder='Enter with product name' name='nome'
                                value={nome} onChange={(e) => onInputChange(e)}
                            />

                        </div>
                        <div className='mb-2'>
                            <label htmlFor='price' className='form-label'>
                                <strong>Price</strong>
                            </label>
                            <input type={"number"} className="form-control" placeholder='Enter with price' name='preco'
                                value={preco} onChange={(e) => onInputChange(e)}
                            />

                        </div>
                        <div className='mb-2'>
                            <label htmlFor='qtd' className='form-label'>
                                <strong>Quantity</strong>
                            </label>
                            <input type={"number"} className="form-control" placeholder='Enter with quantity' name='qtd'
                                value={qtd} onChange={(e) => onInputChange(e)}
                            />
                            <br></br>
                        </div>
                        <div className='mb-5 '>
                            <label htmlFor='categoria' className='form-label' >
                                <strong>Categoria</strong>
                            </label>
                            <select onChange={(e) => handleCategory(e)} className="form-select" aria-label='Default select example' name='categoria'>
                                <option selected>Selected a category</option>
                                {categorias.map((categoria) => (
                                    <option value={categoria.id}>{categoria.nome}</option>

                                ))


                                }

                            </select>
                        </div>

                        <Upload />


                        <button type='submit' className='btn btn-outline-primary'>Submit</button>
                        <Link className='btn btn-outline-danger mx-2' to="/">Cancel</Link>
                    </form>



                </div>
            </div>
        </div>
    );


}

