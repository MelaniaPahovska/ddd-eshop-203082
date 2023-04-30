import {useNavigate, useSearchParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";

const ChangeBook = ({}) => {
    const [searchParams, setSearchParams] = useSearchParams();
    const [data, setData] = useState({})
   const [categories, setCategories] = useState([])
    const [authors, setAuthors] = useState([])
    const navigate = useNavigate()

    useEffect(() => {
        const id = searchParams.get("id")
        if (id != null) {
            axios.get(`http://localhost:8080/api/books/${id}`).then(el => {
                console.log(el.data)
                setData(el.data)
            })
        }
        axios.get(`http://localhost:8080/api/books/categories`).then(el => {
            setCategories(el.data)
        })
        axios.get(`http://localhost:8080/api/authors`).then(el => {
            setAuthors(el.data)
        })
    }, [])

    const submit = () => {
        const id = searchParams.get("id")
        if (id != null) {
            const authorId = data.author && typeof data.author === 'string' ? parseInt(data.author.split(":")[0]) : null;
            axios.put(`http://localhost:8080/api/books/edit/${id}`, {
                ...data,
                authorId
            }).then(el => {
                navigate("/")
            })
        } else {
            const authorId = data.author && typeof data.author === 'string' ? parseInt(data.author.split(":")[0]) : null;
            axios.post(`http://localhost:8080/api/books/add`, {
                ...data,
                authorId
            }).then(el => {
                navigate("/")
            })
        }
    }

    return (
        <div style={{margin: '20px', backgroundColor: '#d1e0e0', padding: '20px', borderRadius: '10px'}}>
            <div className="form-group">
                <label htmlFor="exampleFormControlInput1" style={{color: '#0a4243', fontWeight: 'bold'}}>Title</label>
                <input className="form-control" id="exampleFormControlInput1" value={data.name}
                       onChange={(el) => setData(dt => {
                           return ({...dt, name: el.target.value})
                       })}/>
            </div>
            <div className="form-group">
                <label htmlFor="exampleFormControlSelect1"
                       style={{color: '#0a4243', fontWeight: 'bold'}}>Categories</label>
                <select
                    className="form-control" id="exampleFormControlSelect1" value={data.category}
                    onChange={(el) => setData(dt => {
                        return ({...dt, category: el.target.value})
                    })}>
                    <option>Select Category</option>
                    {categories.map(el => <option>{el}</option>)}
                </select>
            </div>
            <div className="form-group">
                <label htmlFor="exampleFormControlSelect2" style={{color: '#0a4243', fontWeight: 'bold'}}>Author</label>
                <select className="form-control" id="exampleFormControlSelect2" value={data.author}
                        onChange={(el) => setData(dt => ({...dt, author: el.target.value}))}
                        style={{backgroundColor: '#f4ffff', color: '#0a4243'}}>
                    <option>Select Author</option>
                    {authors.map(el => <option key={el.id}>{el.id + ": " + el.name}</option>)}
                </select>
            </div>
            <div className="form-group">
                <label htmlFor="exampleFormControlInput2" style={{color: '#0a4243', fontWeight: 'bold'}}>Copies</label>
                <input className="form-control" id="exampleFormControlInput2" type={'number'}
                       value={data.availableCopies}
                       onChange={(el) => setData(dt => ({...dt, availableCopies: el.target.value}))}
                       style={{backgroundColor: '#f4ffff', color: '#0a4243'}}/>
            </div>
            <br/>
            <button className={'btn btn-primary'} onClick={submit}
                    style={{backgroundColor: '#0a4243', color: '#f4ffff', fontWeight: 'bold'}}>Save Changes
            </button>
        </div>

    )
}

export default ChangeBook;