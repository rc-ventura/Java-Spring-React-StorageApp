import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./Components/Navbar/Navbar";
import Home from "./Pages/Home/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddProduct from "./Produto/AddProduct";
import EditProduct from "./Produto/EditProduct ";
import ViewProduct from "./Produto/ViewProduct";
import AddCategory from "./Produto/AddCategory";

function App() {
  return (

    <div className="App">
      <Router>
        <Navbar />

        <Routes>
          <Route exact path='/' element={<Home />} />
          <Route exact path="/addproduct" element={<AddProduct />} />
          <Route exact path="/addcategory" element={<AddCategory />} />
          <Route exact path="/editProduct/:id" element={<EditProduct/>}/>
          <Route exact path="/viewProduct/:id" element={<ViewProduct/>}/>
        </Routes>

      </Router>
    </div>



  );

}

export default App;
