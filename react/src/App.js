import * as React from "react";
import SignIn from "./page/SignIn";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { DefaultPage } from "./page/DefaultPage";
import SignUp from "./page/Signup";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<DefaultPage />} />
        <Route path="/signin" element={<SignIn />} />
        <Route path="/signup" element={<SignUp />} />

        {/* <Route path="/:id" element={<DetailPage />} /> */}
      </Routes>
    </BrowserRouter>
  );
}
