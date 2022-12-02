import React from 'react'
import { useState, useEffect } from 'react'
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import UploadFileService from '../Services/UploadFile';




export default function AddProduct() {

    let navigate = useNavigate();

    const [product, setProduct] = useState({

        nome: "",
        preco: "",
        qtd: "",
        categoria: "",
        fileStorage: []



    });


    const [file, setFile] = useState('');

    function handleChangeFile(e) {
       setFile(e.target.files[0]);
        handleSubmitFile();
    }


    const handleSubmitFile = async (e) => {
        e.preventDefault();                             // funcao para não carregar a página 
        console.log ('Upload image');
        console.log(file);
        
        UploadFileService.upload(file).then((data) => {console.log(data);
            setProduct ({ 
            ...product, fileStorage: {
                id: data.id,
                data: data.data,
                name: data.name,
                type: data.type,
            }
            })
        });
        
    };
      
  
        
  
  
   
   /*
        UploadFileService.upload(file).then((data) => { console.log(data)
            setProduct({
                ...product, file: {
                    id: data.id,
                    data: data.data,
                    name: data.name,
                    type: data.type,
                }
            })
        });
    };
*/

    


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
        await axios.post("http://localhost:8080/produtos/", product)
        navigate("/");
        alert("Produto cadastrado com sucesso");
    };



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
                                <option >Select a category</option>
                                {categorias.map((categoria) => (
                                    <option defaultValue={categoria.id}>{categoria.nome}</option>

                                ))


                                }

                            </select>
                        </div>
                        <button type='submit' className='btn btn-outline-primary'>Submit</button>
                        <Link className='btn btn-outline-danger mx-2' to="/">Cancel</Link>
                        </form>


                            <div className='mb-5 '>
                            <form onSubmit={handleSubmitFile} >

                              <label htmlFor='image' className='form-label row md-6 ' > 
                                <strong>Imagem</strong>     </label> 
                                <br></br>     
                                <input type="file" name='image' onChange={(e) => handleChangeFile(e)}  />
                                <button type='submit' className='btn btn-outline-primary mx-5 ' > Save </button>
                            </form>



                        </div>


                </div>
            </div>
        </div>
    );


}
