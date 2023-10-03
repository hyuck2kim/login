import React from "react";
import { Link } from "react-router-dom";
import NumberCounter from "../components/NumberCounter";

export const DefaultPage = () => {
  return (
    <div>
      <h1>Hello!!</h1>
      <Link to="/signin">
        <button>로그인</button>
      </Link>
      <NumberCounter />
    </div>
  );
};
