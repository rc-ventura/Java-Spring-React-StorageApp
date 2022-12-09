import React from 'react';
import axios from "axios";
import { useState } from 'react';
import  {useNavigate, Link, useParams} from "react-router-dom";
import { useEffect } from 'react';


export default function EditCategory () {
  

let navigate = useNavigate();

const {id} =useParams();

const [category,setCategory]=useState({
        
    nome: "",
    produtos: "",
});



const [product, setProduct]= useState ({

})

const{nome} = category;

//const [categorias, setCategorias] = useState([]);


//load produtos (UseState produtos)



//const handleCategory = (e) => {
  //setProduct ({...category, produtos: {

    //    id: e.target.value,
      // nome: e.target.options[e.target.selectedIndex].text
    //}
    
    //})
//} 

const loadCategorias=async() => {
    const result = await axios.get(`http://localhost:8080/categorias/${id}`)
    setCategory(result.data);
    console.log(result.data);


}

const onInputChange=(e)=>{
    setCategory({...category, [e.target.name]: e.target.value});

};

useEffect(()=>{

    loadProducts();
    loadCategorias();
},

[]);


const onSubmit= async (e)=> {
    e.preventDefault();
     await axios.put(`http://localhost:8080/categorias/${id}`, category)
     navigate("/addCategory");
     alert("Categoria editada com sucesso");
};


const loadProducts =async() => {

    const result= await axios.get(`http://localhost:8080/produtos/${id}`)
    setProduct(result.data);


}

return (


    <div className='container text-center'>
        <div className='row'>
            <div className='col-md-6 offset md-3 border rounded p-4 mt-1 shadow'>
                <h2 className='text-center m-4'> Edit Category</h2>
                <br />
               <form   onSubmit={(e) => onSubmit(e)}>
                <div className='mb-3'>
                    <label htmlFor='name' className='form-label'>
                        <strong>Nome</strong>
                    </label>
                    <input type={"text"} className="form-control" placeholder='Enter with product name' name='nome'
                     value={nome} onChange={(e)=>onInputChange(e)}
                    />

                </div>
                

              
                <button type='submit' className='btn btn-outline-primary'>Submit</button>
                <Link className='btn btn-outline-danger ' to='/addCategory'>Cancel</Link>
                </form>

            </div>
        </div>
        </div>
);
}
