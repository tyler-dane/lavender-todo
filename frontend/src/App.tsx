import { useEffect, useState } from 'react'
import axios from './utils/axios';
import './App.css'

function App() {
  const [item, setItem] = useState<string>();
  const [refresh, setRefresh] = useState(0);
  const [items, setItems] = useState<{ name: string }[]>();

  useEffect(() => {
    (async () => {
      let response = await axios('/item/all');
      setItems(response.data);
    })()
  }, [refresh])

  async function submit() {
    await axios.put('/item', { name: item });
    setRefresh(Math.random());
    setItem('');
  }

  async function clear() {
    await axios.delete('/item/all');
    setRefresh(Math.random());
  }

  return (
    <div className="flex flex-col items-center w-full">
      <h1 className="p-5 text-3xl text-bold">My Todo List</h1>
      <hr className="h-2 w-full" />
      <div className="mt-4 w-[450px]">
        <div className="flex flex-col">
          <input
            onKeyDown={(e: React.KeyboardEvent) => {
              if (e.key == "Enter") {
                submit()
              }
            }}
            className="p-1 border-black border-2 rounded-md" value={item} onChange={(e) => setItem(e.target.value)} type="text" />
          <button
            className="inline-flex mt-2 items-center rounded-md border border-transparent bg-indigo-600 px-3 py-2 text-sm font-medium leading-4 text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
            onClick={submit}>
            Add Item
          </button>
          <button
            className="inline-flex mt-2 items-center rounded-md border border-transparent bg-red-600 px-3 py-2 text-sm font-medium leading-4 text-white shadow-sm hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2"
            onClick={clear}>
            Clear Items
          </button>
        </div>
        <ul className="mt-2 space-y-2 p-1">
          {items?.map((item) => <li>{item.name}</li>)}
        </ul>
        {items?.length == 0 && <p className="text-gray-600 italic">No items</p>}
      </div>
    </div>
  )
}

export default App
