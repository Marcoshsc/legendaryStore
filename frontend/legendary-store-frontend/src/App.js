import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Product from './Components/Product';

function App() {
  return (
    <Router>
      <div>
        <Switch>
          <Route path="/products">
            <Product id={2} name={"Produto"} stock={2} price={3.2}/>
          </Route>
          <Route path="/sales">
            {null}
          </Route>
          <Route path="/clients">
            {null}
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
