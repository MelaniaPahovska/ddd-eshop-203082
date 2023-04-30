import {useEffect, useState} from "react";
import axios from "axios";

const Categories = () => {
    const [categories, setCategories] = useState([])

    useEffect(()=>{
        axios.get(`http://localhost:8080/api/books/categories`).then(el => {
            console.log(el.data)
            setCategories(el.data)
        })
    },[])
    return(
        <div >
            <ul style={{listStyleType: 'none', color: '#0A4243FF', fontWeight: 'bold'}} >
            {categories.map(el=><li>{el}</li>)}
            </ul>

        </div>
    )
}

export default Categories;