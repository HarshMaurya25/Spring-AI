# AI / LLM Interview Revision Notes

---

## 1. Model

A **model** is a trained AI system that generates text, code, images, and more — accessed via APIs.

| Model Size | Speed | Accuracy | Cost |
|------------|-------|----------|------|
| Small | Fast | Medium | Low |
| Large | Slower | Better | High |

**Examples:** GPT-4.1, Claude, Gemini, Llama

**Interview Qs:**
- What is an LLM and how does it generate responses?

---

## 2. Prompt

A **prompt** is the instruction given to the model. Better prompts → better responses.

### Prompting Types

| Type | Description | Example |
|------|-------------|---------|
| **Zero-shot** | No examples provided | `Explain Dependency Injection.` |
| **One-shot** | One example provided | Show one input→output pair, then ask |
| **Few-shot** | Multiple examples provided | Model learns the expected pattern |
| **System Prompt** | Defines model behavior/role | `You are a senior interviewer. Answer in bullets.` |

### Prompt Engineering Tips
- Be specific and give full context
- Define the model's role and target audience
- Specify output format and constraints

**Interview Qs:**
- What is Prompt Engineering?
- Explain Zero-shot, One-shot, and Few-shot prompting.

---

## 3. Token

A **token** is the smallest unit an LLM processes — not exactly a word, but close.

```
"Artificial Intelligence is amazing." → [Artificial] [Intelligence] [is] [amazing] [.]
```

### Thumb Rule
```
1 Token ≈ 0.75 Word      →      100 Words ≈ 130 Tokens
```

> Code, JSON, and tables consume **more tokens** than plain English.

### Cost Model
```
Total Cost = Input Tokens + Output Tokens
```

### Reduce Token Usage
- Remove unnecessary text
- Send only relevant context
- Summarize chat history
- Limit retrieved documents

**Interview Qs:**
- What is a token? Why do tokens affect pricing?

---

## 4. Context Window

The **context window** is the maximum number of tokens a model can process in a single request.

**It includes everything:**
- System Prompt + User Prompt
- Chat History
- Retrieved Documents
- Model Response

```
Example: Context Window = 128K tokens
  Prompt:     10K
  History:    40K
  Documents:  50K
  Response:   20K
  ─────────────────
  Total:     120K ✅
```

**If exceeded:** older context is dropped, or the request fails.

**Interview Qs:**
- What is a context window? What happens when it's exceeded?

---

## 5. Embedding

An **embedding** converts text into a numerical vector so the model can compare *meaning*, not just exact words.

```
"Artificial Intelligence"  →  [0.23, -0.71, 0.98, ...]
```

Semantically similar text → vectors placed close together in space.

### Why It Matters
```
"How to configure JWT?"   ≈   "JWT Authentication setup"
```
Different words, same meaning — embeddings catch this.

### Embedding Workflow
```
Documents → Split into chunks → Generate Embeddings → Store in Vector DB
                                                              ↓
User Question → Generate Embedding → Similarity Search → Relevant Chunks → LLM
```

**Interview Qs:**
- What are embeddings? Why are they stored in vector databases?

---

## 6. RAG (Retrieval-Augmented Generation)

LLMs only know what they were trained on. **RAG** solves this by retrieving relevant documents *before* generating an answer.

### When RAG is Needed
- Latest / real-time information
- Company documents & internal policies
- Private knowledge bases
- Recently uploaded PDFs

### RAG Flow
```
User Question → Vector DB Search → Retrieve Relevant Chunks → LLM → Answer
```

### Common Vector Databases
`Pinecone` · `PGVector` · `Weaviate` · `Chroma` · `Milvus` · `Elasticsearch`

### RAG vs Fine-tuning

| | RAG | Fine-tuning |
|--|-----|-------------|
| **Best for** | Dynamic / changing knowledge | Specific behavior or style |
| **Updates** | Easy — swap documents | Requires retraining |
| **Cost** | Lower | Higher |

**Interview Qs:**
- Why is RAG needed? Explain the RAG architecture. Difference between RAG and Fine-tuning?

---

## 7. Structured Output

LLMs return plain text by default. **Structured output** forces a predictable format (JSON, XML, YAML) for reliable integration.

```
Plain text:  "John is 28 years old."

JSON:        { "name": "John", "age": 28 }
```

### Benefits
- Easy parsing & validation
- Consistent format
- Reliable API integration
- Less post-processing

**Prompt tip:** `Return ONLY valid JSON. No extra text. Fields: name, experience, skills, location.`

**Interview Qs:**
- Why is structured output preferred? When to use JSON over plain text?

---

## ⚡ One-Line Revision

| Concept | One Line |
|---------|----------|
| **Model** | AI system trained to generate responses |
| **Prompt** | Instruction given to the model |
| **Token** | Smallest unit processed by LLM; drives cost |
| **Context Window** | Max tokens the model handles in one request |
| **Embedding** | Numerical vector of text for semantic search |
| **RAG** | Retrieve relevant docs before generating answers |
| **Structured Output** | JSON/XML output for reliable app integration |