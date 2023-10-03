import React, { useState } from "react";

function NumberCounter() {
  // 초기 숫자 상태를 0으로 설정
  const [number, setNumber] = useState(0);

  // 숫자를 증가시키는 함수
  const incrementNumber = () => {
    setNumber(number + 1);
  };

  // 숫자를 감소시키는 함수
  const decrementNumber = () => {
    setNumber(number - 1);
  };

  return (
    <div style={{ marginTop: "20px" }}>
      숫자 카운터(회원만 할수 있음)
      <br />
      <button onClick={decrementNumber}>-</button>
      <button
        style={{ marginLeft: "5px", marginRight: "10px" }}
        onClick={incrementNumber}
      >
        +
      </button>
      <span>{number}</span>
    </div>
  );
}

export default NumberCounter;
