import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./Components/Navbar/Navbar";
import Home from "./Pages/Home/Home";
import { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddProduct from "./Components/Produto/AddProduct";
import EditProduct from "./Components/Produto/EditProduct ";
import ViewProduct from "./Components/Produto/ViewProduct";
import AddCategory from "./Components/Category/AddCategory";
import EditCategory from "./Components/Category/EditCategory";
import ViewCategory from "./Components/Category/ViewCategory";
import FilterProduct from "./Components/Filters/FilterProduct";
import axios from "axios";

function App() {

  

 //const [products, setProducts] =useState([]);


 useEffect(() => {
     loadProducts();

 }, []);

 const loadProducts =async() => {

     const result= await axios.get("http://localhost:8080/produtos/")
     setNewProductList(result.data);
     console.log(result.data);
 }


 function onFilterValueSelected(filterValue){
  setFilterTextValue(filterValue);
  console.log(filterValue);
}

const[newProductList, setNewProductList] = useState([]);
const[filterTextValue, setFilterTextValue] = useState('Filter by category');

const filteredListProduct = newProductList.filter(function(product) {

  if (filterTextValue === (product.categoria.nome)) {

    const result =  product;
    console.log(result)
   
    return result;


} else {

  return product
}


})

console.log (filteredListProduct)

  return (

    <div className="App">
      <Router>
        <Navbar/>
        <FilterProduct  filterValueSelected={onFilterValueSelected}> </FilterProduct>

        <Routes>
          <Route exact path='/' element={<Home newProductList= {filteredListProduct}>   </Home>} />
          <Route exact path="/addproduct" element={<AddProduct />} />
          <Route exact path="/addCategory" element={<AddCategory />} />
          <Route exact path="/editCategory/:id" element={<EditCategory/>}/>
          <Route exact path="/viewCategory/:id" element={<ViewCategory/>}/>
          <Route exact path="/editProduct/:id" element={<EditProduct/>}/>
          <Route exact path="/editProduct/:id" element={<EditProduct/>}/>
          <Route exact path="/viewProduct/:id" element={<ViewProduct/>}/>
        </Routes>

      </Router>
    </div>



  );

}

export default App;
