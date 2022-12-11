import React from 'react';
import { useState, useEffect } from 'react';
import axios from "axios";



const FilterProduct =(props)=>{



const [categorias, setCategorias] = useState([]);




function onFilterValueChanged(e){
    props.filterValueSelected(e.target.value);
    console.log(e.target.value)
}



const loadCategorias=async() => {
    const result = await axios.get("http://localhost:8080/categorias")
    setCategorias(result.data);
    console.log(result.data);


}


useEffect(()=>{

    
    loadCategorias();
},

[]);


    
        return (

            <nav className="navbar navbar-expand-lg navbar-light bg-">
        <div className="btn-group ">
      <select  onChange={onFilterValueChanged}className="form-select" aria-label='Default select example' name='categoria'>
            
            <option selected>Filter by category</option>
            {categorias.map((categorias) => (
            <option value={categorias.id}>{categorias.nome}</option>

            ))


            }

        </select>
    </div>
    </nav>


    )


} ;


export default FilterProduct;