import React, { useState } from 'react';
import handleChange from './../Util/handleChange';

function Product(props) {
    const [name, setName] = useState(props.name);
    const [stock, setStock] = useState(props.stock);
    const [price, setPrice] = useState(props.price);

    return (
        <div className="product-div">
            <input type="text" value={name} onChange={handleChange(setName)}/>
            <input type="text" value={stock} onChange={handleChange(setStock)}/>
            <input type="text" value={price} onChange={handleChange(setPrice)}/>
        </div>
    )
}

export default Product;